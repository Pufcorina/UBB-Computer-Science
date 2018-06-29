var images = document.body.getElementsByTagName("img");

var score = 0;
var currentShowingIndex = 0;
var remainingSeconds = 60;

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

function addActionToImages(){
  for (i = 0; i < images.length; i++){
    images[i].setAttribute("onclick", "incrementScore()");
  }
}

function hideImage(index){
  images[index].style.display = "none";
}

function showImage(index){
  images[index].style.display = "block";
}

function hideAllImages(){
  for (i = 0; i < images.length; i++){
    hideImage(i);
  }
}

function showRandomImage(){
  var randomIndex = Math.floor(Math.random() * images.length);

  hideImage(currentShowingIndex);
  setRandomPositionForImage(randomIndex);
  showImage(randomIndex);

  currentShowingIndex = randomIndex;
}

function setRandomPositionForImage(index){
  images[index].style.top = randomValueBetween(0, window.innerHeight) + "px";
  images[index].style.left =  randomValueBetween(0, window.innerWidth) + "px";
}

function randomValueBetween(a, b){
  return Math.floor(Math.random() * (b - a)) + a;
}

function incrementScore(){
  console.log('direct hit!');
  score += 1;
}

function decrementRemainngTime(){
  remainingSeconds -= 1;
}

function showScore(){
  document.getElementById("score").innerHTML = '<p> your score is ' + score + '!</p>';
}


function showTime(){
  document.getElementById("time").innerHTML = '<p> remaining: ' + remainingSeconds + 's </p>';
}


resizeImages();
generateGridFromImages(3);
addActionToImages();
hideAllImages();


var imageShowing = setInterval(function() { showRandomImage(); }, 1000);

var countdown = setInterval(function() {
  decrementRemainngTime();
  showTime();
  if (remainingSeconds == 0) {
    showScore();
    clearInterval(countdown);
    clearInterval(imageShowing);

  }
}, 1000);
