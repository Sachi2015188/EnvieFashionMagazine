package team.envie.fashion.enviefashion.entity;

import android.graphics.drawable.Drawable;

/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 *          <p>
 *          ImageData Class
 *          </p>
 */
public class ImageData {

    /**
     * image date title
     */
    private String imageDateTitle;

    /**
     * download pdf url
     */
    private String pdfUrl;

    /**
     * pdf Name after download
     */
    private String pdfName;

    /**
     * Main Screen image resource name
     */
    private Drawable mainImage;

    /**
     * Main Screen content image resource name
     */
    private Drawable contentImage;

    /**
     * Main Screen gallery image resource name
     */
    private Drawable galleryImage;

    /**
     * Constoructor
     * @param imageDateTitle
     * @param pdfUrl
     * @param pdfName
     * @param mainImage
     * @param contentImage
     * @param galleryImage
     */
    public ImageData(String imageDateTitle, String pdfUrl, String pdfName, Drawable mainImage, Drawable contentImage, Drawable galleryImage) {
        this.imageDateTitle = imageDateTitle;
        this.pdfUrl = pdfUrl;
        this.pdfName = pdfName;
        this.mainImage = mainImage;
        this.contentImage = contentImage;
        this.galleryImage = galleryImage;
    }

    public String getImageDateTitle() {
        return imageDateTitle;
    }

    public void setImageDateTitle(String imageDateTitle) {
        this.imageDateTitle = imageDateTitle;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public Drawable getMainImage() {
        return mainImage;
    }

    public void setMainImage(Drawable mainImage) {
        this.mainImage = mainImage;
    }

    public Drawable getContentImage() {
        return contentImage;
    }

    public void setContentImage(Drawable contentImage) {
        this.contentImage = contentImage;
    }

    public Drawable getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(Drawable galleryImage) {
        this.galleryImage = galleryImage;
    }

    @Override
    public String toString() {
        return "ImageData{" +
                "imageDateTitle='" + imageDateTitle + '\'' +
                ", pdfUrl='" + pdfUrl + '\'' +
                ", pdfName='" + pdfName + '\'' +
                ", mainImage=" + mainImage +
                ", contentImage=" + contentImage +
                ", galleryImage=" + galleryImage +
                '}';
    }
}
