import { Select } from "antd";

const { Option } = Select;

export default function TestA(props) {
  console.log(props.teachers);
  const children = [];

  props.teachers.forEach((teacher) => {
    console.log(teacher.id);
    children.push(
      <Option key={teacher.id} value={teacher.id}>
        {teacher.firstName}
      </Option>
    );
  });
  return { children };
}
