import React, { Component } from "react";
import CarDataService from "../services/CarDataService";
import { connect } from "react-redux";
import { updateCar, getOneCar } from "../actions";

function mapStateToProps(state) {
    return { 
        currentCar: state.currentCar,
        name: state.currentCar === null ? null : state.currentCar.name,
        serialNumber: state.currentCar === null ? null :state.currentCar.serialNumber
    };
}

function mapDispatchToProps(dispatch) {
    return {
        getOneCar: () => dispatch(getOneCar(window.bdata.context.code)),
        updateCar: (code, car) => dispatch(updateCar(code, car))
    }
}

class CarComponent extends Component {

    constructor(props) {
        super(props);

        //this.initForm();

        this.getCurrentCar = this.getCurrentCar.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    initForm() {
        this.state = {
            code: window.bdata.context.code,
            car: {code: null, name: null, serialNumber: null},
            message: null,
            name: '',
            serialNumber: ''
        }; 
    }

    componentDidMount() {
        //this.getCurrentCar();
        this.props.getOneCar();
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
        this.updateCar(this.state.code, car);
        this.initForm();
    }

    handleChange(event) {
        let name=event.target.name;
        let value=event.target.value;
        this.setState({[name]: value});
    }

    render() {
        if(this.props.currentCar === null) {
            return (<></>);
        }
        return (
            <>  
                <form onSubmit={this.handleSubmit}>
                <div className="row">
                    <div className="col-6">
                        <div className="form-group">
                            <label >Code</label>
                            <input value={this.props.currentCar.code} name="code" onChange={this.handleChange}  disabled type="text" className="form-control" />
                        </div>
                        <div className="form-group">
                            <label >Name</label>
                            <input value={this.props.currentCar.name} name="name" onChange={this.handleChange}  type="text" className="form-control" />
                        </div>
                        <div className="form-group">
                            <label >Serial Number</label>
                            <input value={this.props.currentCar.serialNumber}  name="serialNumber" onChange={this.handleChange}  type="text" className="form-control" />
                        </div>
                        <input type="hidden" value={this.props.currentCar.version} name="version" onChange={this.handleChange}></input>
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </div>
                </div>
                </form>
            </>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(CarComponent);
