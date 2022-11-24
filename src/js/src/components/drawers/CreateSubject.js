import { Button, Col, Drawer, Form, Input, Row, Select, Space } from "antd";
import { errorNotification } from "../common/Notification";
import { useEffect, useState } from "react";
import axios from "axios";
import { apiUrls } from "../common/variables";
import authHeader from "../services/auth.header";
import ScoolStuff from "../services/SchoolStuff";

const CreateSubject = (props) => {
  const [teachers, setTeachers] = useState([]);
  const children = [];
  const { Option } = Select;

  teachers.forEach((teacher) => {
    console.log(teacher.id);
    children.push(
      <Option key={teacher.id} value={teacher.id}>
        {teacher.firstName}
      </Option>
    );
  });

  const getTeachers = async () => {
    const token = authHeader();
    return await axios
      .get(apiUrls.Users + "teachers", {
        headers: token,
      })
      .then((response) => {
        if (response?.data) {
          setTeachers(response.data);
        }
      });
  };
  useEffect(() => {
    getTeachers();
  }, []);

  const onFinish = (values) => {
    ScoolStuff.saveSubject(values);
  };

  const onFinishFailed = () => {
    errorNotification("Insufficient data", "Please fill all the fields!");
  };

  return (
    <>
      <Drawer
        title="Create a new subject"
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
                name="name"
                label="Name"
                rules={[
                  {
                    required: true,
                    message: "Please enter subject name",
                  },
                ]}
              >
                <Input placeholder="Please enter subject name" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="description"
                label="Description"
                rules={[
                  {
                    required: true,
                    message: "Please add basic description for the subject",
                  },
                ]}
              >
                <Input.TextArea placeholder="max 256" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="teachers"
                label="Teachers"
                rules={[
                  {
                    required: false,
                    type: "array",
                  },
                ]}
              >
                <Select placeholder="Please choose teachers" mode="multiple">
                  {children}
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

export default CreateSubject;
