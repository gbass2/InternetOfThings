import requests
import json

def get_alarm(houseID):
    url = "http://192.168.2.9/49ersense/home/housedetails.php"
    data = {
    	"houseID": houseID,
    }
    
    r = requests.post(url=url,data=data)
    print(r.text)
    
def get_motion_status(houseID,floorID,sensorID):
    

def motion_detector(houseID, alarm):
    url = "http://192.168.2.9/49ersense/home/updategarage.php"
    data = {
            "houseID": houseID,
            "garageID": garageID,
            "garageStatus": garage_status
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def main():
    houseID = 1
    get_alarm(houseID)
    

if __name__=="__main__":
    main()
