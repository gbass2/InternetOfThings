import requests
import json

def update_garage(houseID, garageID, garage_status):
    url = "http://192.168.2.9/49ersense/home/updategarage.php";
    data = {
            "houseID": houseID,
            "garageID": garageID,
            "garageStatus": garage_status
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def update_house(houseID, security_status):
    url = "http://192.168.2.9/49ersense/home/updatehousedetails.php";
    data = {
            "houseID": houseID,
            "security": security_status
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def update_light(houseID, floorID, lightID, dim_level, light_status):
    url = "http://192.168.2.9/49ersense/home/updatelights.php";
    data = {
            "houseID": houseID,
            "floorID": floorID,
            "lightID": lightID,
            "lightStatus": light_status,
            "dimmerLevel": dim_level
            }
    print(data)
    r = requests.post(url=url,data=data)
    print(r.text)

def update_lock(houseID, doorID, lock_status):
    url = "http://192.168.2.9/49ersense/home/updatelocks.php";
    data = {
            "houseID": houseID,
            "doorID": json.dumps(doorID),
            "lockStatus": json.dumps(lock_status)
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def update_fan(houseID, floorID, fan):
    url = "http://192.168.2.9/49ersense/home/updatefan.php";
    data = {
            "houseID": houseID,
            "floorID": floorID,
            "fan": fan
            }
    r = requests.post(url=url,data=data)
    print(r.text)

def update_mode(houseID, floorID, mode):
    url = "http://192.168.2.9/49ersense/home/updatemode.php";
    data = {
            "houseID": houseID,
            "floorID": floorID,
            "mode": mode
            }
    r = requests.post(url=url,data=data)
    print(r.text)


def update_temperature(houseID, floorID, control_temp):
    url = "http://192.168.2.9/49ersense/home/updatecontroltemp.php";
    data = {
            "houseID": houseID,
            "floorID": floorID,
            "controlTemp": control_temp
            }
    r = requests.post(url=url,data=data)
    print(r.text)
