import React, { useState, useEffect } from "react";
import CarDataService from "../services/CarDataService";


const CarNewComponent = (props) => {
    const [name, setName] = useState('')
    const [serialNumber, setSerialNumber] = useState('')
    

    const handleSubmit = (event) => {
        event.preventDefault(); 
        const car = {
            name: name,
            serialNumber: serialNumber 
        };
        CarDataService.createcar(car).then(
            response => {  
                console.log(response);
                setName('')
                setSerialNumber('')
            }
        );

    }

   
    return (
        <>
            <form onSubmit={handleSubmit}>
            <div className="row">
                <div className="col-6"> 
                    <div className="form-group">
                        <label >Name</label>
                        <input value={name} name="name" onChange={e => {setName(e.target.value)}}  type="text" className="form-control" />
                    </div>
                    <div className="form-group">
                        <label >Serial Number</label>
                        <input value={serialNumber}  name="serialNumber" onChange={e => {setSerialNumber(e.target.value)}}  type="text" className="form-control" />
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </div>
            </div>
            </form>
        </>
    )
    
}

export default CarNewComponent
