import React from 'react';
import ReactDOM  from "react-dom"; 
import RouteComponent from './js/components/RouteComponent';

const wrapper = document.getElementById("container");

wrapper? ReactDOM.render(
    <React.StrictMode>
        <RouteComponent />
    </React.StrictMode>
    ,
    wrapper): false;


