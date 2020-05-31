import React, { Component } from "react"; 

import CarDataService from '../services/CarDataService';
import PagesTools from "../tools/PagesTools";

class ListCarsComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            cars: [],
            messages: null
        }
        this.refreshCourses = this.refreshCourses.bind(this);
    }

    componentDidMount() {
        this.refreshCourses();
    }

    refreshCourses() {
        CarDataService.retrieveAllCars()
            .then(
                response => {  
                    this.setState({cars: response.data})
                }
            );
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
                        this.state.cars.map(
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

export default ListCarsComponent
