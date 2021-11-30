import requests
import json

def update_lock(houseID, doorID, lock_status):
    url = "http://127.0.0.1/49ersense/home/updatelocks.php";
    data = {
            "houseID": houseID,
            "doorID": json.dumps(doorID),
            "lockStatus": json.dumps(lock_status)
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def main():
    lock_status = [1,0,1]
    doorID = [1,2,3]
    houseID = 1;

    update_lock(houseID, doorID, lock_status)

if __name__=="__main__":
    main()
