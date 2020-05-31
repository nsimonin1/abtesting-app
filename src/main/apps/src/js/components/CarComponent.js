import React, { Component } from "react";
import CarDataService from "../services/CarDataService";

class CarComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            code: window.bdata.context.code,
            car: {code: null, name: null, serialNumber: null},
            message: null,
            name: '',
            serialNumber: ''
        };
        this.getCurrentCar = this.getCurrentCar.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    componentDidMount() {
        this.getCurrentCar();
    }

    getCurrentCar() {         
        const code = this.state.code;
        CarDataService.retrieveOneCare(code).then(
            response=> {
                this.setState(
                    {
                        car: response.data,
                        name: response.data.name,
                        serialNumber: response.data.serialNumber
                     }
                    );
            }
        ); 
    }

    handleSubmit(event) {
        event.preventDefault();  
        const car = {
            name: this.state.name,
            serialNumber: this.state.serialNumber,
            version: this.state.car.version,
        };
        CarDataService.updateCar(this.state.code, car).then(
            response => {  
                this.getCurrentCar();
            }
        );
    }

    handleChange(event) {
        let name=event.target.name;
        let value=event.target.value;
        this.setState({[name]: value});
    }

    render() {
        if(this.state.car.code == null) {
            return (<></>);
        }
        return (
            <>  
                <form onSubmit={this.handleSubmit}>
                <div className="row">
                    <div className="col-6">
                        <div className="form-group">
                            <label >Code</label>
                            <input value={this.state.car.code} name="code" onChange={this.handleChange}  disabled type="text" className="form-control" />
                        </div>
                        <div className="form-group">
                            <label >Name</label>
                            <input value={this.state.name} name="name" onChange={this.handleChange}  type="text" className="form-control" />
                        </div>
                        <div className="form-group">
                            <label >Serial Number</label>
                            <input value={this.state.serialNumber}  name="serialNumber" onChange={this.handleChange}  type="text" className="form-control" />
                        </div>
                        <input type="hidden" value={this.state.car.version} name="version" onChange={this.handleChange}></input>
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </div>
                </div>
                </form>
            </>
        )
    }
}

export default CarComponent
