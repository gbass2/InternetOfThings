import requests
from datetime import datetime
import time
import os

chunk_size=1024
file_size= 5242880


url="http://192.168.2.8:8000/video_feed"
url1="http://192.168.2.8:8000/motion_detect"
while 1:
    # Create timestamp
    t = datetime.now().strftime("%Y-%b-%Y-%H:%M:%S")
    t= t.replace(":","-")
    r1=requests.get(url1,stream=True)

    print(r1.text)
    if (r1.text == "True"):

    # Append timestamp to file name
        with open(f"{t}.mp4","ab") as f:
            r= requests.get(url,stream=True)
            for chunk in r.iter_content(chunk_size=chunk_size):
                # print(len(chunk))
                if len(chunk) == chunk_size:
                    f.write(chunk)
                    if int(os.path.getsize(f"{t}.mp4"))>file_size:
                        f.close()
                        break
    else:continue
