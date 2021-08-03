import React from "react";  
import CarsComponent from "./CarsComponent";
import PagesTools from "../tools/PagesTools";
import CarOneComponent from "./CarOneComponent";

const MainComponent = () => {
    if(PagesTools.isPages('carsList')) {
        return ( <CarsComponent /> );
    } else if(PagesTools.isPages('carsEdit')) {
        return ( <CarOneComponent />)
    }
    else {
        return ( <h2>Vide</h2>);
    }
}
 

export default MainComponent;

