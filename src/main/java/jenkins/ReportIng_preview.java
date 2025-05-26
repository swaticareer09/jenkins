package jenkins;


//Anush

public class ReportIng_preview {
  private String s3Url;

  public ReportIng_preview(String s3Url) {
      this.s3Url = s3Url;
  }

  // HTML to open the image in a popup
  public String getWindowOpenHtml() {
      return "<a href='#' onclick=\"openImagePopup('" + s3Url + "'); return false;\">" +
              "<embed src='" + s3Url + "' width='150' height='100' style='cursor: pointer; border: 2px solid black;' /></a>";
  }

  // scripting for image popup functionality
  public String getPopupScript() {
      return "<script>" +
              "function openImagePopup(imageUrl) {" +
              "  var popupDiv = document.createElement('div');" +
              "  popupDiv.style.position = 'fixed';" +
              "  popupDiv.style.top = '0';" +
              "  popupDiv.style.left = '0';" +
              "  popupDiv.style.width = '100vw';" +
              "  popupDiv.style.height = '100vh';" +
              "  popupDiv.style.backgroundColor = 'rgba(0, 0, 0, 0.8)';" +
              "  popupDiv.style.display = 'flex';" +
              "  popupDiv.style.alignItems = 'center';" +
              "  popupDiv.style.justifyContent = 'center';" +
              "  popupDiv.style.overflow = 'hidden';" +
              "  var image = document.createElement('img');" +
              "  image.src = imageUrl;" +
              "  image.style.maxWidth = '90vw';" +
              "  image.style.maxHeight = '90vh';" +
              "  image.style.objectFit = 'contain';" +
              "  image.style.border = '2px solid white';" +
              "  popupDiv.appendChild(image);" +
              "  var closeButton = document.createElement('button');" +
              "  closeButton.innerHTML = '&times;';" +
              "  closeButton.style.position = 'fixed';" +
              "  closeButton.style.top = '20px';" +
              "  closeButton.style.right = '20px';" +
              "  closeButton.style.padding = '10px';" +
              "  closeButton.style.backgroundColor = 'transparent';" +
              "  closeButton.style.color = '#fff';" +
              "  closeButton.style.border = 'none';" +
              "  closeButton.style.fontSize = '48px';" + 
              "  closeButton.style.cursor = 'pointer';" +
              "  closeButton.onclick = function() {" +
              "    document.body.removeChild(popupDiv);" +
              "  };" +
              "  popupDiv.appendChild(closeButton);" +
              "  document.body.appendChild(popupDiv);" +
              "}" +
              "</script>";
  }
}
