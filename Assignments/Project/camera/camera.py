#!/usr/bin/python
import cv2
import time
import datetime
from motion_detection import SingleMotionDetector
from threading import Thread, Lock

class Camera():
    # Constructor...
    def __init__(self):
            self.cap = cv2.VideoCapture(0)   # Prepare the camera...
            print("Camera warming up ...")
            time.sleep(1)

            # Prepare Capture
            self.ret, self.frame = self.cap.read()

            Thread(target=self.get_frame,args=()).start()
            # self._db_lock = Lock()

    # Frame generation for Browser streaming wiht Flask...
    def get_frame(self):
            self.captureVideo()
            self.frames = open("stream.jpg", 'wb+')

            if self.ret:    # frame captures without errors...
                    cv2.imwrite("stream.jpg", self.frame)   # Save image...

            # return self.frames.read()

            return

    def captureVideo(self):
            # Read in a new frame...
            self.ret, self.frame = self.cap.read()
            cv2.normalize(self.frame, self.frame, 5, 500, cv2.NORM_MINMAX)
            timestamp = datetime.datetime.now()
            cv2.putText(self.frame, timestamp.strftime(
                    "%A %d %B %Y %I:%M:%S%p"), (10, self.frame.shape[0] - 10),
                    cv2.FONT_HERSHEY_SIMPLEX, 0.35, (0, 0, 255), 1)

            return()
