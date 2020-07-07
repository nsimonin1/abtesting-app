import React, { Component } from "react";
import { connect } from "react-redux";
import { getCars } from "../actions/index";

function mapStateToProps(state) {
    return {
        cars: state.cars
    }
}

function mapDispatchToProps (dispatch) {
   return {
    getCars: () => dispatch(getCars())
   }
}

export class CarsComponent extends Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() { 
       this.props.getCars();
    }

    render() {
        return (  
            <table className="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Serial Number</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        this.props.cars.map(
                            (car, i) => 
                            <tr key={car.code}>
                                <td>{i+1}</td>
                                <td>{car.code}</td>
                                <td>{car.name}</td>
                                <td>{car.serialNumber}</td>
                                <td>
                                    <a href={'./cars/edit?code='+car.code}>edit</a>
                                </td>
                            </tr>
                        ) 
                    } 
                </tbody>
            </table> 
        );
    }

}



export default connect(
    mapStateToProps,
    mapDispatchToProps
    )(CarsComponent); 