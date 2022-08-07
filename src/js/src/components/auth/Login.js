import React from "react";
import { Button, Form, Input } from "antd";
import AuthService from "../services/auth.service";
import { errorNotification } from "../common/Notification";

export default function Login(props) {
  // const [logged, setLogged] = React.useState(false);

  const onFinishFailed = (errorInfo) => {
    console.log(errorInfo);
    errorNotification();
  };

  function formSubmitHandler(values) {
    AuthService.login(values.username, values.password);
    if (AuthService.getCurrentUser) {
      props.handleLogin();
    }
  }

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
