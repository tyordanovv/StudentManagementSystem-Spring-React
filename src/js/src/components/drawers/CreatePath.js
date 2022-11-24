import {
  Button,
  Col,
  Radio,
  Drawer,
  Form,
  Input,
  Row,
  Select,
  Space,
} from "antd";
import AuthService from "../services/auth.service";
import { errorNotification } from "../common/Notification";
import { useState } from "react";
import SelectSubjects from "./SelectSubjects";

const CreatePath = (props) => {
  const [type, setType] = useState("");
  const subjects = [];

  const onFinish = (values) => {
    var semesterSubjects = {
      1: values.semester1,
      2: values.semester2,
      3: values.semester3,
      4: values.semester4,
      5: values.semester5,
      6: values.semester6,
    };

    if (values.semester7) {
      semesterSubjects[7] = values.semester7;
      semesterSubjects[8] = values.semester8;
    }

    const pathRequest = {
      description: values.description,
      name: values.name,
      type: values.pathType,
      semesterSubjects,
    };

    console.log(JSON.stringify(pathRequest));
  };

  const onFinishFailed = () => {
    errorNotification("Insufficient data", "Please fill all the fields!");
    console.log("fail");
  };

  return (
    <>
      <Drawer
        title="Create a new path"
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
                name="pathType"
                label="Path Type"
                rules={[{ required: true, message: "Please pick an type!" }]}
              >
                <Radio.Group>
                  <Radio value="bachelor" onChange={() => setType("bachelor")}>
                    Bachelor
                  </Radio>
                  <Radio value="master" onChange={() => setType("master")}>
                    Master
                  </Radio>
                </Radio.Group>
              </Form.Item>
            </Col>
          </Row>
          <SelectSubjects type={type} subjects={subjects} />
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
