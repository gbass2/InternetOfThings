#!/usr/bin/env python
from flask import Flask, render_template, Response, send_file
import cv2
import socket
from skimage import io

# emulated camera
from camera import Camera

# If you are using a webcam -> no need for changes
# if you are using the Raspberry Pi camera module (requires picamera package)
# from camera_pi import Camera

app = Flask(__name__)

@app.route('/')
def index():
    """Video streaming home page."""
    return render_template('index.html')

def generate():
    while 1:
        try:
            frame = cv2.imread('stream.jpg',1)
            _, frame = cv2.imencode(".jpg", frame)
            yield(b'--frame\r\n' b'Content-Type: image/jpeg\r\n\r\n' +
                bytearray(frame) + b'\r\n')
        except:
            continue


camera = Camera()
@app.route('/save_feed')
def save_video_():
    """Video streaming route. Put this in the src attribute of an img tag."""
    while True:
        frame = camera.get_frame()
        return send_file('stream.jpg',as_attachment=True, attachment_filename='stream.jpg',mimetype='image/jpeg')

@app.route('/video_feed')
def video_feed():
    return Response(generate(), mimetype = "multipart/x-mixed-replace; boundary=frame")

if __name__ == '__main__':
    hostname = socket.gethostname()
    ip = socket.gethostbyname(hostname)
    print(ip)
    app.run(host=ip, port=8000, threaded=True)
