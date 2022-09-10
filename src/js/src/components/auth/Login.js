import React, { useState } from "react";
import { Button, Form, Input } from "antd";
import AuthService from "../services/auth.service";
import { useContext } from "react";
import AuthContext, { useAuth } from "../common/AuthProvider";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const auth = useAuth();
  const navigate = useNavigate();

  const onFinishFailed = (errorInfo) => {
    console.log(errorInfo);
  };

  const formSubmitHandler = (values) => {
    AuthService.login(values.username, values.password);
    const user = AuthService.getCurrentUser();
    auth.login(user);
    if (auth.auth) {
      navigate("/home", { replace: true });
    }
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
