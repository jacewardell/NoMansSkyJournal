package com.sadostrich.nomansskyjournal.Models;

import com.google.gson.annotations.SerializedName;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/31/16.
 */
public class DiscoveryImage {
	private String id;
	@SerializedName("_user")
	private String userId, name, mimeType, extension, filepath, filename;
	@SerializedName("fileurl")
	private FileUrl fileUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public FileUrl getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(FileUrl fileUrl) {
		this.fileUrl = fileUrl;
	}

	public class FileUrl {
		private String winQuadratic, carousel, carouselThumb, thumb;
		@SerializedName("fullhd")
		private String fullHd;
		@SerializedName("uploadthumb")
		private String uploadThumb;
		@SerializedName("adminthumb")
		private String adminThumb;

		public String getWinQuadratic() {
			return winQuadratic;
		}

		public void setWinQuadratic(String winQuadratic) {
			this.winQuadratic = winQuadratic;
		}

		public String getCarousel() {
			return carousel;
		}

		public void setCarousel(String carousel) {
			this.carousel = carousel;
		}

		public String getCarouselThumb() {
			return carouselThumb;
		}

		public void setCarouselThumb(String carouselThumb) {
			this.carouselThumb = carouselThumb;
		}

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = thumb;
		}

		public String getFullHd() {
			return fullHd;
		}

		public void setFullHd(String fullHd) {
			this.fullHd = fullHd;
		}

		public String getUploadThumb() {
			return uploadThumb;
		}

		public void setUploadThumb(String uploadThumb) {
			this.uploadThumb = uploadThumb;
		}

		public String getAdminThumb() {
			return adminThumb;
		}

		public void setAdminThumb(String adminThumb) {
			this.adminThumb = adminThumb;
		}
	}
}
