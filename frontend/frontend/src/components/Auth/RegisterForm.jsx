import { Grid2, Typography } from "@mui/material";

import React from "react";
import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../State/Authentication/Action";
import { useDispatch } from "react-redux";
import { useFormik } from "formik";

let intialValues = {
  name: "",
  email: "",
  password: "",
  role: "",
};

const RegisterForm = () => {
  const dispatch = useDispatch();

  const Formik = useFormik({
    initialValues: intialValues,
    onSubmit: (values) => {
      console.log(values);
      dispatch(registerUser({ userData: values, navigate }));
    },
  });

  const navigate = useNavigate();
  return (
    <div>
      <Typography variant="h5" className="text-center">
        Register
      </Typography>
      <form onSubmit={Formik.handleSubmit}>
        <Grid2 container spacing={2} margin={2}>
          <div className="relative w-full">
            <label
              htmlFor="name"
              className="absolute top-[-10px] left-2 text-sm z-2 bg-black px-1"
              style={{ color: "#e91e63" }}
            >
              Name
            </label>
            <input
              type="text"
              name="name"
              id="name"
              placeholder="FullName"
              className="effect  h-[50px] rounded-[4px]  bg-black  p-3 w-full outline-none z-0 border-[1px] border-gray-500 placeholder:text-md"
              autoComplete="off"
              value={Formik.values.name}
              onBlur={Formik.handleBlur}
              onChange={Formik.handleChange}
            />
          </div>
          <div className="relative w-full">
            <label
              htmlFor="email"
              className="absolute top-[-10px] left-2 text-sm z-2 bg-black px-1"
              style={{ color: "#e91e63" }}
            >
              Email
            </label>
            <input
              type="text"
              name="email"
              id="email"
              placeholder="Email"
              className="effect  h-[50px] rounded-[4px]  bg-black  p-3 w-full outline-none z-0 border-[1px] border-gray-500 placeholder:text-md"
              autoComplete="off"
              value={Formik.values.email}
              onBlur={Formik.handleBlur}
              onChange={Formik.handleChange}
            />
          </div>

          <div className=" relative w-full">
            <label
              htmlFor="Password"
              className="absolute top-[-10px] left-2 text-sm z-2 bg-black px-1"
              style={{ color: "#e91e63" }}
            >
              Password
            </label>
            <input
              type="password"
              name="password"
              id="password"
              placeholder="Password"
              autoComplete="off"
              className="effect h-[50px] rounded-[4px]  bg-black outline-2 p-3 w-full outline-none z-0 border-[1px] border-gray-500 placeholder:text-md"
              value={Formik.values.password}
              onBlur={Formik.handleBlur}
              onChange={Formik.handleChange}
            />
          </div>
          <div className=" relative w-full">
            <label
              htmlFor="role"
              className="absolute top-[-10px] left-2 text-sm z-2 bg-black px-1"
              style={{ color: "#e91e63" }}
            >
              Select Role
            </label>
            <select
              style={{ color: "#A9A9A9" }}
              type="text"
              name="role"
              id="role"
              className="effect h-[50px] rounded-[4px]  bg-black outline-2 p-3 w-full outline-none z-0 border-[1px] border-gray-500 placeholder:text-md"
              value={Formik.values.role}
              onBlur={Formik.handleBlur}
              onChange={Formik.handleChange}
            >
              <option value="SELECT_ROLE" className="text-gray-500">
                Select Role
              </option>
              <option value="ROLE_CUSTOMER">Customer</option>
              <option value="ROLE_RESTAURANT_OWNER">Restaurant Owner</option>
            </select>
          </div>

          <Button type="submit" className="wi" variant="contained" fullWidth>
            Submit
          </Button>
        </Grid2>
      </form>
      <Typography sx={{ mt: 3 }} variant="body2" align="center">
        Already have an account
        <Button onClick={() => navigate("/account/login")} size="small">
          Login
        </Button>
      </Typography>
    </div>
  );
};

export default RegisterForm;
