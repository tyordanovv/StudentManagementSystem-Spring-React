import React, { useEffect, useState } from "react";
import "antd/dist/antd.css";
import { BookOutlined, UserOutlined, SettingOutlined } from "@ant-design/icons";
import { Menu } from "antd";
// import AuthService from "../services/auth.service";
import { userTypes } from "../common/variables";

function getItem(label, key, icon, children, type) {
  return {
    key,
    icon,
    children,
    label,
    type,
  };
}

const SideMenu = (props) => {
  console.log(1);
  console.log(props);
  console.log(2);
  console.log(props.user);
  const [theme, setTheme] = useState("dark");
  const [current, setCurrent] = useState("1");
  const [menuItems, setMenuItems] = useState([]);

  //   const changeTheme = (value) => {
  //     setTheme(value ? "dark" : "light");
  //   };

  const onClick = (e) => {
    console.log("click ", e);
    setCurrent(e.key);
  };

  useEffect(() => {
    if (props.user.roles.includes(userTypes.Admin)) {
      console.log("vutre");
      setMenuItems([
        getItem("Programs", "sub1", <BookOutlined />, [
          getItem("View Path", "1", null, []),
          getItem("View Subjets", "2", null, []),
          getItem("Create Path", "3"),
          getItem("Create Subject", "4"),
        ]),
        getItem("Users", "sub2", <UserOutlined />, [
          getItem("View Students", "5"),
          getItem("View Staff Members", "6", null, [
            getItem("Teachers", "7"),
            getItem("Assistants", "8"),
          ]),
          getItem("Add User", "sub3"),
        ]),
        getItem("Profile", "sub4", <SettingOutlined />, [
          getItem("Settings", "9"),
          getItem("Messages", "10"),
        ]),
      ]);
      // }
    }
  }, [props]);
  return (
    <>
      {/* <Switch
        checked={theme === "dark"}
        onChange={changeTheme}
        checkedChildren="Dark"
        unCheckedChildren="Light"
      />
      <br />
      <br /> */}
      <Menu
        theme={theme}
        onClick={onClick}
        style={{
          width: 256,
        }}
        defaultOpenKeys={["sub1"]}
        selectedKeys={[current]}
        mode="inline"
        items={menuItems}
      />
    </>
  );
};

export default SideMenu;
