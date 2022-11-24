import { Select, Form, Row } from "antd";
import { useEffect, useState } from "react";
import authHeader from "../services/auth.header";
import axios from "axios";
import { apiUrls } from "../common/variables";
// import { Select } from "antd";

export default function SelectSubjects(props) {
  const semesters = [];
  const children = [];
  const [subjects, setSubjects] = useState([]);
  const { Option } = Select;

  subjects.forEach((subject) => {
    children.push(
      <Option key={subject.id} value={subject.id}>
        {subject.name}
      </Option>
    );
  });

  const handleChange = (e) => {
    console.log(e);
    console.log(...e);
  };
  const getSubjects = async () => {
    const token = authHeader();
    return await axios
      .get(apiUrls.SchoolStuff + "get-subjects", {
        headers: token,
      })
      .then((response) => {
        if (response?.data) {
          setSubjects(response?.data);
        }
      });
  };

  useEffect(() => {
    getSubjects();
  }, []);

  for (let i = 1; i < 7; i++) {
    semesters.push(
      <Form.Item
        key={i}
        name={`semester${i}`}
        label={`Semester ${i}`}
        rules={[
          {
            required: false,
            type: "array",
          },
        ]}
        direction="vertical"
        style={{
          width: "100%",
        }}
      >
        <Select
          placeholder={`Please choose semester ${i} subjects`}
          mode="multiple"
          maxTagCount={2}
          style={{
            padding: "10px",
          }}
          onChange={handleChange}
        >
          {children}
        </Select>
      </Form.Item>
    );
  }
  if (props.type === "master") {
    for (let i = 7; i < 9; i++) {
      semesters.push(
        <Form.Item
          key={i}
          name={`semester${i}`}
          label={`Semester ${i}`}
          rules={[
            {
              required: false,
              type: "array",
            },
          ]}
          direction="vertical"
          style={{
            width: "100%",
          }}
        >
          <Select
            name={`semester${i}`}
            key={i}
            placeholder={`Please choose semester ${i} subjects`}
            mode="multiple"
            maxTagCount={2}
            style={{
              padding: "10px",
            }}
            onChange={handleChange}
          >
            {children}
          </Select>
        </Form.Item>
      );
    }
  }
  return semesters;
}
