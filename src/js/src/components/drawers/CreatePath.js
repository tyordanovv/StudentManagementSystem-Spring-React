import {
  Button,
  Col,
  DatePicker,
  Drawer,
  Form,
  Input,
  Row,
  Select,
  Space,
} from "antd";
import AuthService from "../services/auth.service";
import { errorNotification } from "../common/Notification";

const CreatePath = (props) => {
  function padTo2Digits(number) {
    return number.toString().padStart(2, "0");
  }

  function formatDate(date) {
    return [
      padTo2Digits(date.getDate()),
      padTo2Digits(date.getMonth() + 1),
      date.getFullYear(),
    ].join("/");
  }

  const onDateChange = (date, dateString) => {
    console.log(date, dateString);
  };

  const onFinish = (values) => {
    const user = {
      firstName: values.firstName,
      lastName: values.lastName,
      birthday: formatDate(values.birthday._d),
      email: values.email,
      password: values.password,
      roles: values.type,
      address: values.address,
      gender: values.gender,
      number: values.number,
    };
    console.log(user);
    AuthService.register(user);
  };

  const onFinishFailed = () => {
    errorNotification("Insufficient data", "Please fill all the fields!");
    console.log("fail");
  };

  return (
    <>
      <Drawer
        title="Create a new account"
        width={720}
        onClose={props.onClose}
        visible={props.visible}
        bodyStyle={{
          paddingBottom: 80,
        }}
      >
        <Form
          layout="vertical"
          hideRequiredMark
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
        >
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="firstName"
                label="First Name"
                rules={[
                  {
                    required: true,
                    message: "Please enter user first name",
                  },
                ]}
              >
                <Input placeholder="Please enter user first name" />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="lastName"
                label="Last Name"
                rules={[
                  {
                    required: true,
                    message: "Please enter user last name",
                  },
                ]}
              >
                <Input placeholder="Please enter user last name" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="address"
                label="Address"
                rules={[
                  {
                    required: true,
                    message: "Please enter user address",
                  },
                ]}
              >
                <Input placeholder="Please enter user address" />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="birthday"
                label="Birthday"
                rules={[
                  {
                    required: true,
                    message: "Please enter your birthday",
                  },
                ]}
              >
                <DatePicker onChange={onDateChange} />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="gender"
                label="Gender"
                rules={[
                  {
                    required: true,
                    message: "Please choose gender",
                  },
                ]}
              >
                <Select placeholder="Please choose gender">
                  {/* <Option value="male">Male</Option>
                  <Option value="female">Female</Option> */}
                </Select>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="number"
                label="Number"
                rules={[
                  {
                    required: true,
                    message: "Please enter user number",
                  },
                ]}
              >
                <Input
                  maxLength="9"
                  type="number"
                  addonBefore="+359 "
                  placeholder="Please enter user phone number"
                />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="email"
                label="Email"
                rules={[
                  {
                    required: true,
                    message: "Please enter user email",
                  },
                  {
                    type: "email",
                    message: "The input is not valid E-mail!",
                  },
                ]}
              >
                <Input placeholder="Please enter user email" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="password"
                label="Password"
                rules={[
                  {
                    required: true,
                    message: "Please enter user password",
                  },
                ]}
                hasFeedback
              >
                <Input.Password placeholder="Please enter user password" />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="confirm-password"
                label="Confirm Password"
                dependencies={["password"]}
                hasFeedback
                rules={[
                  {
                    required: true,
                    message: "Please confirm user password",
                  },
                  ({ getFieldValue }) => ({
                    validator(_, value) {
                      if (!value || getFieldValue("password") === value) {
                        return Promise.resolve();
                      }

                      return Promise.reject(
                        new Error(
                          "The two passwords that you entered do not match!"
                        )
                      );
                    },
                  }),
                ]}
              >
                <Input.Password placeholder="Please confirm user password" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="type"
                label="Type"
                rules={[
                  {
                    required: true,
                    message: "Please choose the type",
                  },
                ]}
              >
                <Select placeholder="Please choose the type">
                  {/* <Option value="teacher">Teacher</Option>
                  <Option value="assistant">Assistant</Option>
                  <Option value="student">Student</Option> */}
                </Select>
              </Form.Item>
            </Col>
          </Row>
          <Space>
            <Button onClick={props.onClose}>Cancel</Button>
            <Button type="primary" htmlType="submit">
              Submit
            </Button>
          </Space>
        </Form>
      </Drawer>
    </>
  );
};

export default CreatePath;
