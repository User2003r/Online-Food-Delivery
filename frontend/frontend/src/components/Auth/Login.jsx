import { login } from "../State/Authentication/Action";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useFormik } from "formik";
import "../Auth/Login/Login.css";
import { Grid2, Typography, Button } from "@mui/material";

const intialValues = {
  email: "",
  password: "",
};

const Login = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const Formik = useFormik({
    initialValues: intialValues,
    onSubmit: (values) => {
      console.log(values);
      dispatch(login({ userData: values, navigate }));
    },
  });

  // console.log(Formik);
  return (
    <div>
      <Typography variant="h5" className="text-center">
        Login
      </Typography>
      <form onSubmit={Formik.handleSubmit}>
        <Grid2 container spacing={2} margin={2}>
          <div className="relative w-full">
            <label
              htmlFor="Email"
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

          <Grid2 size={12}>
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
          </Grid2>

          <Button type="submit" className="wi" variant="contained" fullWidth>
            Submit
          </Button>
        </Grid2>
      </form>
      <Typography sx={{ mt: 3 }} variant="body2" align="center">
        Don't have an account
        <Button onClick={() => navigate("/account/register")} size="small">
          register
        </Button>
      </Typography>
    </div>
  );
};

export default Login;
