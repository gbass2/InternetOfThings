import requests 
from datetime import datetime 
import time
import os

chunk_size=1024
file_size= 52428800


url="http://127.0.0.1:3360/video_feed"
url1="http://127.0.0.1:3360/motion_detect"
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
    