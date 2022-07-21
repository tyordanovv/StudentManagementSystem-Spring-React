import UserService from "../services/user.service";
// import NavigationBar from "./NavigationBar";
// import SideMenu from "./SideMenu";
import {
  // DesktopOutlined,
  // FileOutlined,
  // PieChartOutlined,
  TeamOutlined,
  UserOutlined,
  BookOutlined,
  SettingOutlined,
} from "@ant-design/icons";
import { Layout, Menu, PageHeader } from "antd";
import React, { useState, useEffect } from "react";

const { Content, Footer, Sider } = Layout;

function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}

const items = [
  // getItem("Profile", "profile", <UserOutlined />),
  // getItem("Option 1", "1", <PieChartOutlined />),
  // getItem("Option 2", "2", <DesktopOutlined />),
  // getItem("User", "sub1", <UserOutlined />, [
  //   getItem("Tom", "3"),
  //   getItem("Bill", "4"),
  //   getItem("Alex", "5"),
  // ]),
  // getItem("Team", "sub2", <TeamOutlined />, [
  //   getItem("Team 1", "6"),
  //   getItem("Team 2", "8"),
  // ]),
  // getItem("Files", "9", <FileOutlined />),
  getItem("Profile", "profile", <UserOutlined />),
  getItem("Programs", "sub1", <BookOutlined />, [
    getItem("View Path", "1", null, []),
    getItem("View Subjets", "2", null, []),
    getItem("Create Path", "3"),
    getItem("Create Subject", "4"),
  ]),
  getItem("Users", "sub2", <TeamOutlined />, [
    getItem("View Students", "5"),
    getItem("View Staff Members", "6", null, [
      getItem("Teachers", "7"),
      getItem("Assistants", "8"),
    ]),
    getItem("Add User", "sub3"),
  ]),
  getItem("Settings", "sub4", <SettingOutlined />, [
    getItem("Settings", "9"),
    getItem("Messages", "10"),
  ]),
];

const Home = (props) => {
  const [content, setContent] = useState("");
  const [collapsed, setCollapsed] = useState(false);

  useEffect(() => {
    UserService.getPublicContent().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        const _content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
        setContent(_content);
      }
    );
  }, [content]);
  return (
    // <div className="home-page">
    //   <NavigationBar user={props.user} handleLogout={props.handleLogout} />
    //   {/* <h1>Home</h1> */}
    //   <SideMenu user={props.user} />
    // </div>

    <div>
      <Layout
        style={{
          minHeight: "100vh",
        }}
      >
        <Sider
          collapsible
          collapsed={collapsed}
          onCollapse={(value) => setCollapsed(value)}
        >
          <div className="logo" />
          <Menu
            theme="dark"
            defaultSelectedKeys={["1"]}
            mode="inline"
            items={items}
          />
        </Sider>
        <Layout className="site-layout">
          <PageHeader
            className="site-page-header"
            title="Student Management System"
            subTitle="Admin View"
          />
          <Content
            style={{
              margin: "0 16px",
            }}
          >
            <div
              className="site-layout-background"
              style={{
                padding: 24,
                minHeight: 360,
              }}
            >
              Bill is a cat.
            </div>
          </Content>
          <Footer
            style={{
              textAlign: "center",
            }}
          >
            Ant Design ©2018 Created by Ant UED
          </Footer>
        </Layout>
      </Layout>
    </div>
  );
};
export default Home;
