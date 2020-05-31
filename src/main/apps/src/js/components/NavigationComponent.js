import React, { Component } from "react";
import ListCarsComponent from "./ListCarsComponent";
import PagesTools from "../tools/PagesTools";
import CarNewComponent from "./CarNewComponent";
import CarComponent from "./CarComponent";

class NavigationComponent extends Component {
 


    render() {
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

export default NavigationComponent;