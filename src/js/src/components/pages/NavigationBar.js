import React from "react";

export default function NavigationBar(props) {
  return (
    <div className="topnav">
      <a className="active" href="#">
        Student Management System
      </a>
      <a href="#">Home</a>
      <a href="#">Contact</a>
      <a href="#" onClick={props.handleLogout}>
        LogOut
      </a>
    </div>
  );
}
