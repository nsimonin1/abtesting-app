import React from "react";
import ListCarsComponent from "./ListCarsComponent";
import CarNewComponent from "./CarNewComponent";
import CarComponent from "./CarComponent";
import {BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

const RouteComponent = () => { 
    
    return (
        <Router> 
            <Switch> 
                <Route exact path='/cars' component={ListCarsComponent}></Route> 
                <Route path='/cars/new' component={CarNewComponent}></Route> 
                <Route path='/cars/edit' component={CarComponent}></Route> 
            </Switch> 
        </Router> 
    )
    
}

export default RouteComponent;