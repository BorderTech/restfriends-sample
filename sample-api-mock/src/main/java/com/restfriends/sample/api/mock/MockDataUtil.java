package com.restfriends.sample.api.mock;

import com.restfriends.sample.api.exception.SampleBusinessException;
import com.restfriends.sample.api.model.Application;
import com.restfriends.sample.api.model.Attachment;
import com.restfriends.sample.api.model.AttachmentContent;
import com.restfriends.sample.api.model.types.DocumentType;
import com.restfriends.sample.api.model.types.LevelType;
import com.restfriends.sample.api.model.types.StatusType;
import java.io.InputStream;
import java.util.*;
import org.apache.commons.io.IOUtils;

/**
 * Mock implementation for testing.
 */
public class MockDataUtil {

	public static final String APPLICATION_SYSTEM_EXCEPTION_ID = "error";
	public static final String APPLICATION_BUSINESS_EXCEPTION_ID = "error2";
	public static final String APPLICATION_NOT_FOUND_ID = "not found";

	private static final Map<String, Application> APPLICATIONS = new HashMap<>();
	private static final HashMap<String, byte[]> REDACT_CACHE = new HashMap();
	private static final HashMap<String, byte[]> DOC_CACHE = new HashMap();

	public static void resetData() {
		APPLICATIONS.clear();
		REDACT_CACHE.clear();
	}

	public static Application getOrCreateApplication(final String applicationId)
			throws SampleBusinessException {
		Application appl = APPLICATIONS.get(applicationId);
		if (appl == null) {
			appl = createApplication(applicationId);
			APPLICATIONS.put(applicationId, appl);
		}
		return appl;
	}

	public static void saveRedactImage(final String key, final byte[] image) {
		REDACT_CACHE.put(key, image);
	}

	public static byte[] getRedactImage(final String key) {
		return REDACT_CACHE.get(key);
	}

	public static AttachmentContent getApplicationAttachment(final String key) {
		threadSleep();
		String path = "/sample/docs/sample1.pdf";
		AttachmentContent content = new AttachmentContent(key, getDocumentBytes(path), path, getDocumentMimeType(path));
		return content;
	}

	public static AttachmentContent getAttachmentContent(final Attachment attachment) {

		// Sleep
		threadSleep();

		// CHECK Redact CACHE
		String path = attachment.getFileName();
		String key = attachment.getApplicationId() + "-" + attachment.getAttachmentKey();
		byte[] bytes = getRedactImage(key);
		if (bytes == null) {
			bytes = getDocumentBytes(path);
		}
		AttachmentContent content = new AttachmentContent(attachment.getAttachmentKey(), bytes, path, getDocumentMimeType(path));
		return content;
	}

	private static void threadSleep() {
		// Sleep for 3 seconds (simulate a real call)
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new IllegalStateException("Could not process mock service call as thread interrupted. " + e.getMessage(), e);
		}
	}

	private static Application createApplication(final String applicationId) {
		Application appl = new Application(applicationId);
		appl.setAttachments(createAttachments(applicationId));
		appl.setLevel(LevelType.GOLD);
		appl.setFirstName("David");
		appl.setLastName("Hume");
		appl.setStatus(StatusType.SUBMITTED);
		return appl;
	}

	private static List<Attachment> createAttachments(final String applicationId) {
		// Build mock list of attachments
		boolean lots = applicationId.endsWith("lots");
		int reps = lots ? 15 : 1;
		List<Attachment> docs = new ArrayList<>();
		int idx = 0;
		for (int x = 0; x < reps; x++) {
			boolean suffix = x > 0;
			for (String name : new String[]{"Einstein", "Bohr", "Maxwell", "Curie", "Schrodinger", "Feynman", "Young", "Roentgen"}) {
				docs.add(createImageDocument(applicationId, idx++, name, suffix));
			}
			docs.add(createImageDocument(applicationId, idx++, "FireSafety1", suffix));
			docs.add(createImageDocument(applicationId, idx++, "FireSafety2", suffix));
			// Word docs
			for (String name : new String[]{"document1", "document2"}) {
				docs.add(createWordDocument(applicationId, idx++, name, suffix));
			}
			// PDFs
			for (String name : new String[]{"sample1", "sample2", "smallEncrypted", "smallEncrypted"}) {
				docs.add(createPdfDocument(applicationId, idx++, name, suffix));
			}
		}
		return docs;
	}

	private static Attachment createImageDocument(final String applicationId, final int idx, final String name, final boolean suffix) {
		Attachment doc = new Attachment(applicationId, "id-" + idx);
		String qual = suffix ? "_" + idx : "";
		doc.setDescription(name + qual);
		doc.setFileName("/sample/images/" + name + ".jpg");
		doc.setSavedTimestamp(createDate(idx));
		byte[] bytes = getDocumentBytes(doc.getFileName());
		doc.setFileSize(Long.valueOf(bytes.length));
		setupDocTypes(doc, idx);
		return doc;
	}

	private static Attachment createPdfDocument(final String applicationId, final int idx, final String name, final boolean suffix) {
		Attachment doc = new Attachment(applicationId, "id-" + idx);
		String qual = suffix ? "_" + idx : "";
		doc.setDescription(name + qual);
		doc.setFileName("/sample/docs/" + name + ".pdf");
		doc.setSavedTimestamp(createDate(idx));
		byte[] bytes = getDocumentBytes(doc.getFileName());
		doc.setFileSize(Long.valueOf(bytes.length));
		setupDocTypes(doc, idx);
		return doc;
	}

	private static Attachment createWordDocument(final String key, final int idx, final String name, final boolean suffix) {
		Attachment doc = new Attachment(key, "id-" + idx);
		String qual = suffix ? "_" + idx : "";
		doc.setDescription(name + qual);
		doc.setFileName("/sample/docs/" + name + ".docx");
		doc.setSavedTimestamp(createDate(idx));
		byte[] bytes = getDocumentBytes(doc.getFileName());
		doc.setFileSize(Long.valueOf(bytes.length));
		setupDocTypes(doc, idx);
		return doc;
	}

	private static Date createDate(final int idx) {
		if (idx % 5 == 0) {
			return null;
		}
		int yr = 2010 - (idx % 4);
		int mth = 12 - (idx % 3);
		int day = 28 - (idx % 7);
		Calendar dt = Calendar.getInstance();
		dt.set(yr, mth, day);
		return dt.getTime();
	}

	private static void setupDocTypes(final Attachment doc, final int idx) {
		int items = DocumentType.values().length;
		doc.setDocumentType(DocumentType.values()[idx % items]);
	}

	private static byte[] getDocumentBytes(final String resourcePath) {
		// Check already cached
		if (DOC_CACHE.containsKey(resourcePath)) {
			return DOC_CACHE.get(resourcePath);
		}
		// Load file
		try (InputStream stream = MockDataUtil.class.getResourceAsStream(resourcePath)) {
			byte[] bytes = IOUtils.toByteArray(stream);
			// Save in cache
			DOC_CACHE.put(resourcePath, bytes);
			return bytes;
		} catch (Exception e) {
			throw new IllegalStateException("Error loading resource." + e.getMessage(), e);
		}
	}

	private static String getDocumentMimeType(final String resourcePath) {
		// Just the MIME Types for the MOCK Data
		if (resourcePath.endsWith("jpg")) {
			return "image/jpg";
		} else if (resourcePath.endsWith("pdf")) {
			return "application/pdf";
		} else if (resourcePath.endsWith("docx")) {
			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		} else {
			return "";
		}
	}

}
