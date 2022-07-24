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

const { Option } = Select;

const Register = (props) => {
  const onChange = (date, dateString) => {
    console.log(date, dateString);
  };

  const formSubmitHandler = () => {};

  const onFinishFailed = () => {};

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
        extra={
          <Space>
            <Button onClick={props.onClose}>Cancel</Button>
            <Button onClick={() => AuthService.register} type="primary">
              Submit
            </Button>
          </Space>
        }
      >
        <Form
          layout="vertical"
          hideRequiredMark
          onFinish={(values) => formSubmitHandler(values)}
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
                name="email"
                label="Email"
                rules={[
                  {
                    required: true,
                    message: "Please enter user email",
                  },
                ]}
              >
                <Input placeholder="Please enter user email" />
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
                <DatePicker onChange={onChange} />
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
              >
                <Input placeholder="Please enter user password" />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="confirm-password"
                label="Confirm Password"
                rules={[
                  {
                    required: true,
                    message: "Please confirm user password",
                  },
                ]}
              >
                <Input placeholder="Please confirm user password" />
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
                  <Option value="teacher">Teacher</Option>
                  <Option value="assistant">Assistant</Option>
                  <Option value="student">Student</Option>
                </Select>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Drawer>
    </>
  );
};

export default Register;
