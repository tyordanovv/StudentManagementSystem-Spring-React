import React from "react";
import { Button, Form, Input } from "antd";
import AuthService from "../services/auth.service";
import { useContext } from "react";
import AuthContext from "../common/AuthProvider";

export default function Login(props) {
  const { setAuth } = useContext(AuthContext);

  const onFinishFailed = (errorInfo) => {
    console.log(errorInfo);
  };

  const formSubmitHandler = (values) => {
    AuthService.login(values.username, values.password, setAuth);
  };

  return (
    <div className="login-form">
      <Form
        name="basic"
        labelCol={{
          span: 8,
        }}
        wrapperCol={{
          span: 16,
        }}
        initialValues={{
          remember: true,
        }}
        onFinish={(values) => formSubmitHandler(values)}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item
          label="Username"
          name="username"
          rules={[
            {
              required: true,
              message: "Please input your username!",
            },
          ]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          label="Password"
          name="password"
          rules={[
            {
              required: true,
              message: "Please input your password!",
            },
          ]}
        >
          <Input.Password />
        </Form.Item>

        <Form.Item
          wrapperCol={{
            offset: 8,
            span: 16,
          }}
        >
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
}
