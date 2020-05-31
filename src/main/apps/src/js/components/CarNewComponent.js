import React, { Component } from "react";
import CarDataService from "../services/CarDataService";


class CarNewComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            serialNumber: ''
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        
    }

    handleChange(event) {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({[name]: value});
    }

    handleSubmit(event) {
        event.preventDefault(); 
        const car = {
            name: this.state.name,
            serialNumber: this.state.serialNumber 
        };
        CarDataService.createcar(car).then(
            response => {  
                console.log(response);
            }
        );

    }

    render() {
        return (
            <>
                <form onSubmit={this.handleSubmit}>
                <div className="row">
                    <div className="col-6"> 
                        <div className="form-group">
                            <label >Name</label>
                            <input value={this.state.name} name="name" onChange={this.handleChange}  type="text" className="form-control" />
                        </div>
                        <div className="form-group">
                            <label >Serial Number</label>
                            <input value={this.state.serialNumber}  name="serialNumber" onChange={this.handleChange}  type="text" className="form-control" />
                        </div>
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </div>
                </div>
                </form>
            </>
        )
    }
}

export default CarNewComponent
