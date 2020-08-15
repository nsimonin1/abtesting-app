import React, { useState, useEffect } from "react";
import CarDataService from "../services/CarDataService";

const CarComponent = (props) => {
    const [code, setCode] = useState(window.bdata.context.code)
    const [car, setCar] = useState({code: null, name: null, serialNumber: null})
    const [message, setMessage] = useState(null)
    const [name, setName] = useState('')
    const [serialNumber, setSerialNumber] = useState('')
    const [version, setVersion] = useState('') 

 const getCurrentCar = () => {     
        CarDataService.retrieveOneCare(code).then(
            response=> {
                setCar(response.data)
                setName(response.data.name)
                setSerialNumber(response.data.serialNumber)   
                setVersion(response.data.version)             
            }
        ); 
    }
    
    useEffect(() => {
        getCurrentCar()
    }, []) 

   

const handleSubmit = (event) => {
        event.preventDefault();  
        const car = {
            name: name,
            serialNumber: serialNumber,
            version: version,
        };
        CarDataService.updateCar(code, car).then(
            response => {  
                getCurrentCar();
            }
        );
    }

    if(car.code == null) {
        return (<></>)
    }

    return (
        <>  
            <form onSubmit={handleSubmit}>
            <div className="row">
                <div className="col-6">
                    <div className="form-group">
                        <label >Code</label>
                        <input value={code} name="code" onChange={e=> setCode(e.target.value)}   disabled type="text" className="form-control" />
                    </div>
                    <div className="form-group">
                        <label >Name</label>
                        <input value={name} name="name" onChange={e=> setName(e.target.value)}  type="text" className="form-control" />
                    </div>
                    <div className="form-group">
                        <label >Serial Number</label>
                        <input value={serialNumber}  name="serialNumber" onChange={e => setSerialNumber(e.target.value)}  type="text" className="form-control" />
                    </div>
                    <input type="hidden" value={version} name="version" onChange={e =>setVersion(e.target.value) }></input>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </div>
            </div>
            </form>
        </>
    )

}

export default CarComponent
