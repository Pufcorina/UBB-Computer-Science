var images = document.body.getElementsByTagName("img");

function insertAfter(newNode, referenceNode) {
    referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
}

function resizeImages(){
  for (i = 0; i < images.length; i++){
    images[i].height = 100;
    images[i].width = 100;
  }
}

function generateGridFromImages(cols){
  for (i = 0; i < images.length; i++){
    if ((i + 1) % cols == 0) {
      insertAfter(document.createElement("br"), images[i]);
    }
  }
}

resizeImages();
generateGridFromImages(3);
