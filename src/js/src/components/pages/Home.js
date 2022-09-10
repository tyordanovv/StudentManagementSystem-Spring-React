import UserService from "../services/user.service";
import Register from "../auth/Register";
import {
  TeamOutlined,
  UserOutlined,
  BookOutlined,
  SettingOutlined,
} from "@ant-design/icons";
import { Layout, Menu, PageHeader } from "antd";
import React, { useState, useEffect } from "react";
import CreatePath from "../drawers/CreatePath";
import CreateSubject from "../drawers/CreateSubject";
import { useAuth } from "../common/AuthProvider";

const { Content, Footer, Sider } = Layout;

function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}

const Home = (props) => {
  const auth = useAuth();
  const [content, setContent] = useState("");
  const [collapsed, setCollapsed] = useState(false);
  const [visibleRegisterDrawer, setVisibleRegisterDrawer] = useState(false);
  const [visiblePathDrawer, setVisiblePathDrawer] = useState(false);
  const [visibleSubjectDrawer, setVisibleSubjectDrawer] = useState(false);

  const showRegisterDrawer = () => {
    setVisibleRegisterDrawer(true);
  };

  const showPathDrawer = () => {
    setVisiblePathDrawer(true);
  };
  const showSubjectDrawer = () => {
    setVisibleSubjectDrawer(true);
  };
  const onClose = () => {
    setVisibleRegisterDrawer(false);
    setVisiblePathDrawer(false);
    setVisibleSubjectDrawer(false);
  };

  const items = [
    getItem("Homepage", "homepage", <UserOutlined />),
    getItem("Profile", "profile", <UserOutlined />),
    getItem("Programs", "sub1", <BookOutlined />, [
      getItem("View Path", "1", null, []),
      getItem("View Subjets", "2", null, []),
      getItem(
        <a href="#" onClick={showPathDrawer}>
          Create Path
        </a>,
        "3"
      ),
      getItem(
        <a href="#" onClick={showSubjectDrawer}>
          Create Subject
        </a>,
        "4"
      ),
    ]),
    getItem("Users", "sub2", <TeamOutlined />, [
      getItem("View Students", "5"),
      getItem("View Staff Members", "6", null, [
        getItem("Teachers", "7"),
        getItem("Assistants", "8"),
      ]),
      getItem(
        <a href="#" onClick={showRegisterDrawer}>
          Add User
        </a>,
        "sub3"
      ),
    ]),
    getItem("Settings", "sub4", <SettingOutlined />, [
      getItem("Settings", "9"),
      getItem("Messages", "10"),
    ]),
    getItem(<a onClick={props.handleLogout}>Logout</a>, "logout", null),
  ];

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
    <div>
      <Register
        onClose={onClose}
        showDrawer={showRegisterDrawer}
        visible={visibleRegisterDrawer}
      />
      <CreatePath
        onClose={onClose}
        showDrawer={showPathDrawer}
        visible={visiblePathDrawer}
      />
      <CreateSubject
        onClose={onClose}
        showDrawer={showSubjectDrawer}
        visible={visibleSubjectDrawer}
      />
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
