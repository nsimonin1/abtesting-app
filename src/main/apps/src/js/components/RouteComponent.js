import React, { Component } from "react";
import ListCarsComponent from "./ListCarsComponent";
import PagesTools from "../tools/PagesTools";
import CarNewComponent from "./CarNewComponent";
import CarComponent from "./CarComponent";
import {BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

class RouteComponent extends Component {
 
    render() {
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

    render1() {
        if(PagesTools.isPages('carsList')) {
            return (
                <>
                  <ListCarsComponent />
                </>
            );
        } else if(PagesTools.isPages('carsNew')) {
            return (
                <>
                  <CarNewComponent />
                </>
            );
        } else if(PagesTools.isPages('carsEdit')) {
            return (
                <>
                  <CarComponent />
                </>
            );
        }
        return (
            <>
              <h1>Page not support</h1>
            </>
        );
    }
}

export default RouteComponent;