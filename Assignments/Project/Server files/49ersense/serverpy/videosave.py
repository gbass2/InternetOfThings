import requests
from datetime import datetime
import time
import os
from pathlib import Path
import cv2
import numpy as np
from motion_detection import SingleMotionDetector

# import thread module
from _thread import *
import threading

def save():
    url="http://192.168.1.198:8000/save_feed"
    md = SingleMotionDetector(accumWeight=.01)
    w     = 640	# Frame width...
    h     = 480		# Frame hight...
    fps   = 25      # Frames per second...

    # Define the codec and create VideoWriter object
    fourcc = cv2.VideoWriter_fourcc(*'avc1')
    i = 0
    j = 0
    t = datetime.now().strftime("%b-%Y-%H:%M:%S")
    t = t + ':' + str(datetime.now().microsecond)
    t = t.replace(":","-") + '.mp4'
    t = Path(r'/opt/lampp/htdocs/49ersense/videos') / t
    out = cv2.VideoWriter(str(t),fourcc, fps, (w,h), True)
    while 1:
        try:
            # Create timestamp
            r= requests.get(url)
            nparr = np.frombuffer(r.content, np.uint8)
            frame = cv2.imdecode(nparr, cv2.COLOR_BGR2GRAY)
            out.write(frame)
            gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
            gray = cv2.GaussianBlur(gray, (7, 7), 0)

            if i == 0:
            	md.update(gray)

            motion = md.detect(gray)

            if motion is not None:
                out.write(frame)
                j=1

            else:
                if j==1:
                    t = 'DNE.mp4'

                file_path = os.path.join(os.path.dirname(os.path.realpath(__file__)),t)
                if os.path.exists(file_path):
                    os.remove(file_path)

                t = datetime.now().strftime("%Y-%b-%Y-%H:%M:%S")
                t = t + ':' + str(datetime.now().microsecond)
                t = t.replace(":","-") + '.mp4'
                t = Path(r'/opt/lampp/htdocs/49ersense/videos') / t

                j=0
                out.release()
                out = cv2.VideoWriter(str(t),fourcc, fps, (w,h), True)

            md.update(gray)
            i=1

        except Exception as e:
            print(e)
            continue

    out.release()

thread = threading.Thread(target=save,daemon=True)
thread.start()
