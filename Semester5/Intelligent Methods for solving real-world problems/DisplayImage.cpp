#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <iostream>
#include <stdio.h>
using namespace std;
using namespace cv;

static const Size trainingPadding = Size(0, 0);
static const Size winStride = Size(8, 8);

void computeHOGforAnImage(string &imageFileName, vector<float> &featureVector){
						//vector of features (real numbers) extracted from the image){
	Mat imageData = imread(imageFileName, 0);
	HOGDescriptor* hog = new HOGDescriptor;
	hog->winSize = Size(18, 36);
	hog->blockSize = Size(8, 8);
	hog->blockStride = Size(2,2);
	if (imageData.empty()) {
        featureVector.clear();
        printf("Error: HOG image is empty, features calculation skipped!\n");
        return;
	}
	    // Check for mismatching dimensions
	if (imageData.cols != hog->winSize.width || imageData.rows != hog->winSize.height) {
		featureVector.clear();
		printf("Error: Image dimensions (%u x %u) do not match HOG window size (%u x %u)!\n", imageData.cols, imageData.rows, hog->winSize.width, hog->winSize.height);
	    return;
	}
	vector<Point> locations;
	hog->compute(imageData, featureVector, winStride, trainingPadding, locations);


	imageData.release(); // Release the image again after features are extracted
	delete hog;
}



void displayAnImage(string fileName){
	Mat img = imread(fileName.c_str(), CV_LOAD_IMAGE_GRAYSCALE);
	namedWindow("MyWindow", CV_WINDOW_NORMAL);
	imshow("MyWindow", img);

	waitKey(0);
}

void displayAnImageColor(string fileName){
	Mat img = imread(fileName.c_str(), CV_LOAD_IMAGE_COLOR);
	namedWindow("MyWindow", CV_WINDOW_NORMAL);
	imshow("MyWindow", img);

	waitKey(0);
}
int main(){

	displayAnImageColor("D:\\_Work\\Programe\\openCV\\images\\image.tif");
	displayAnImage("D:\\_Work\\Programe\\openCV\\images\\image.tif");

	string imgFile("D:\\_Work\\Programe\\openCV\\images\\trainSplitOnFolders\\pedestrian\\1-140\\img_00112.pgm");
	vector<float> features;
	computeHOGforAnImage(imgFile, features);

	for(int i = 0; i < features.size(); i++){
			cout << features.at(i) << " ";
		}

	return 0;
}
