import {
  Box,
  Button,
  Card,
  Divider,
  Grid2,
  Modal,
  TextField,
} from "@mui/material";
import React, { useState } from "react";
import CartItem from "./CartItem";
import AddressCard from "./AddressCard";
import AddLocationAltIcon from "@mui/icons-material/AddLocationAlt";
import * as Yup from "yup";
import { ErrorMessage, Field, Formik } from "formik";
import { Form } from "react-bootstrap";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  outline: "none",
  p: 4,
};

const intialValues = {
  streetaddress: "",
  state: "",
  city: "",
  pincode: "",
};

const Cart = () => {
  const createOrderUsingSelectedAddress = () => {};
  const openNewAddressModal = () => {
    setOpen(true);
  };
  const [open, setOpen] = useState(false);
  const handleClose = () => setOpen(false);

  const validationSchema = Yup.object().shape({
    streetAddress: Yup.string().required("Street Address is required"),
    state: Yup.string().required("State is required"),
    city: Yup.string().required("City is required"),
    pincode: Yup.number().required("Pincode is required"),
  });

  const handleSubmit = (values) => {
    console.log("form values", values);
  };

  return (
    <div>
      <main className="lg:flex justify-between">
        <section className="lg:w-[30%] space-y-6 lg:min-h-screen pt-10">
          {[1, 1].map((item) => {
            return <CartItem />;
          })}
          <div className="billDetails px-5 text-sm">
            <p className="font-extralight py-5">Bill Details</p>
            <div className="space-y-3">
              <div className="flex justify-between text-gray-400">
                <p>Item Total</p>
                <p>Rs 1200</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>Delivery Fee</p>
                <p>Rs 21</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>Platform Fee</p>
                <p>Rs 5</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>GST and Restaurant Charges</p>
                <p>Rs 25</p>
              </div>
            </div>
            <Divider sx={{ my: 2 }} />
            <div className="flex justify-between text-gray-400">
              <p>Total Pay</p>
              <p>Rs 1251</p>
            </div>
          </div>
        </section>
        <Divider orientation="vertical" flexItem />
        <section className="lg:w-[70%] flex justify-center px-5 pb-10 lg:pb-0">
          <div>
            <h1 className="text-center font-semibold text-2xl py-10">
              Choose Delivery Address
            </h1>
            <div className="flex gap-5 flex-wrap justify-center">
              {[1, 1, 1].map((add) => (
                <AddressCard
                  showButton={true}
                  handleSelectAddress={createOrderUsingSelectedAddress}
                />
              ))}
              <Card className="flex gap-5 w-64 p-5">
                <AddLocationAltIcon />
                <div className="space-y-3 text-gray-500">
                  <h2 className="text-lg text-white">Add New Address</h2>
                  <Button
                    variant="outlined"
                    fullWidth
                    onClick={() => openNewAddressModal()}
                  >
                    ADD
                  </Button>
                </div>
              </Card>
            </div>
          </div>
        </section>
      </main>
      <div>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={style}>
            <Formik
              intialValues={intialValues}
              validationSchema={validationSchema}
              onSubmit={handleSubmit}
            >
              <Form>
                <Grid2 container spacing={2}>
                  <Grid2 size={12}>
                    <Field
                      type="text"
                      as={TextField}
                      name="streetaddress"
                      label="Street Address"
                      fullWidth
                      variant="outlined"
                    />
                  </Grid2>
                  <Grid2 size={6}>
                    <Field as={TextField} name="state" label="State" />
                  </Grid2>
                  <Grid2 size={6}>
                    <Field as={TextField} name="pincode" label="Pincode" />
                  </Grid2>
                  <Grid2 size={12}>
                    <Field as={TextField} name="city" label="City" fullWidth />
                  </Grid2>
                  <Grid2 size={12}>
                    <Button type="submit" variant="contained" fullWidth>
                      Submit
                    </Button>
                  </Grid2>
                </Grid2>
              </Form>
            </Formik>
          </Box>
        </Modal>
      </div>
    </div>
  );
};

export default Cart;
