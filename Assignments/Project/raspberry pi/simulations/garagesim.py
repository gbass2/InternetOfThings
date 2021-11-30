import requests
import json

def update_garage(houseID, garageID, garage_status):
    url = "http://127.0.0.1/49ersense/home/updategarage.php";
    data = {
            "houseID": houseID,
            "garageID": garageID,
            "garageStatus": garage_status
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def main():
    garage_status = [1,0] # Front Lock, Back Lock, garage Lock
    garageID = [1,2]
    houseID = 1;

    for i in range(len(garageID)):
        update_garage(houseID, garageID[i], garage_status[i])

if __name__=="__main__":
    main()
