import React, { useState, useEffect } from 'react';
import "./Navbar.scss";
import { Link, useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
// import { fetchCategories } from '../../store/categorySlice';
// import { getCartTotal } from '../../store/cartSlice';
import { links } from "./../../utils/constants";
import { getCart } from '../../actions/cartActions';
import { Form, Modal, Nav, NavDropdown } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import { getUserDetails, logout, updateUserProfile } from '../../actions/userActions';
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';
import { Button } from 'react-bootstrap'


const Navbar = () => {
  const [name, setName] = useState('')
  const [phone, setPhone] = useState('')
  const [address, setAddress] = useState('')
  const [message, setMessage] = useState('')

  const dispatch = useDispatch()
  const navigate = useNavigate()

  const userUpdateProfile = useSelector(state => state.userUpdateProfile)
  const { success: successUpdate } = userUpdateProfile

  // const {data: categories} = useSelector((state) => state.category);
  // const {totalItems} = useSelector((state => state.cart));

  const userLogin = useSelector(state => state.userLogin)
  const { userInfo } = userLogin
  console.log(userInfo);

  const { carts } = useSelector(state => state.cartList)
  // const { cartItems } = cart
  console.log('-=-=', carts)
  console.log(links);

  useEffect(() => {
    dispatch(getCart())
    if (successUpdate) {
      window.location.reload()
    } else {
      if (!userInfo?.name) {
        dispatch(getUserDetails(userInfo?.id))
      } else {
        setName(userInfo?.name)
        setPhone(userInfo?.phone)
        setAddress(userInfo?.address)
      }
    }
  }, [dispatch, navigate, successUpdate])

  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  // useEffect(() => {
  //   dispatch(fetchCategories());
  //   dispatch(getCartTotal());
  //   // eslint-disable-next-line react-hooks/exhaustive-deps
  // }, []);
  const logoutHandler = () => {
    dispatch(logout())
    navigate('/')
    window.location.reload()
  }

  const renderMenuControl = () => {
    if (
      userInfo?.role === "role_admin"
    ) {
      return
    } else {
      return null;
    }
  };
  //search
  const productList = useSelector(state => state.productList)
  const { products } = productList
  console.log('==', products?.data?.list)

  // Search
  const myOptions = [];
  const getDataSearch = (product) => {
    product?.data?.list?.forEach(prod => {
      myOptions.push(prod.name)
    })
  }

  getDataSearch(products)

  const [selectedOptions, setSelectedOptions] = useState('');

  const handleSubmit = () => {
    // console.log('==', selectedOptions);
    products?.data?.list?.find(prod => {
      if (prod.name === selectedOptions) {
        navigate(`/products/${prod.id}`)
      }
    })
  }

  // Update Profile Shipper
  const [showInfo, setShowInfo] = useState(false);
  const handleCloseInfo = () => setShowInfo(false);
  const handleShowInfo = () => {
    setShowInfo(true);
  }

  // Update profile Admin
  const updateHandler = () => {
    if (name.trim().length === 0 || phone.trim().length === 0 || address.trim().length === 0) {
      setMessage("Vui l??ng ??i???n ????? th??ng tin")
    } else {
      setShowInfo(false);
      dispatch(updateUserProfile(userInfo.id, { name: name, phone: phone, address: address }))
      const user = JSON.parse(localStorage.getItem('userInfo'))
      localStorage.setItem('userInfo', JSON.stringify({ ...user, name: name, phone: phone, address: address }))
    }
  }

  return (
    <nav className="navbar1">
      <div className='navbar1-content'>
        <div className="container">
          <div className="navbar1-top flex flex-between">
            <Link to="/" className="navbar1-brand">
              <span className="text-regal-blue">Electric'S</span><span className='text-gold'>STORE.</span>
            </Link>

            <form className="navbar1-search flex">
              <div>
                <Autocomplete disablePortal options={myOptions.sort()} onChange={(event, value) => setSelectedOptions(value)}
                  renderInput={(params) => (
                    <TextField
                      style={{
                        minWidth: '30rem',
                        width: "100%",
                        border: "1px solid black",
                        color: "$clr-light-blue",
                        paddingLeft: "4px",
                        fontSize: "medium",
                      }}
                      className="navbar1-search-input"
                      {...params}
                      InputProps={{ ...params.InputProps, disableUnderline: true }}
                      placeholder='Nh???p t??n s???n ph???m c???n t??m, v?? d???: C???m bi???n h???ng ngo???i'
                    />
                  )}
                />
              </div>
              {/* <div className='d-flex align-items-center mb-5 py-0 px-3 shadow-sm p-3 mb-5 bg-white rounded' style={{ background: '#ffffff', borderRadius: '10px', border: 'solid 1px #3CB371' }}>
                <div className='w-100'>
                    <Autocomplete disablePortal options={myOptions.sort()} onChange={(event, value) => setSelectedOptions(value)}
                        renderInput={(params) => (
                            <TextField
                                {...params}
                                InputProps={{ ...params.InputProps, disableUnderline: true }}
                                placeholder='Nh???p t??n s???n ph???m c???n t??m, v?? d???: C?? chua'
                            />
                        )}
                    />
                </div>

                </div> */}
              {/* <button type = "submit" className = "navbar1-search-btn">
                  <i className = "fas fa-search"></i>
                </button> */}
              <div>
                <button onClick={handleSubmit} className="navbar1-search-btn">
                  <i className="fas fa-search"></i>
                </button>
              </div>
            </form>

            <div className="navbar1-btns">
              <Link to="/cart" className="add-to-cart-btn flex">
                <span className="btn-ico">
                  <i className="fas fa-shopping-cart"></i>
                </span>
                <div className='btn-txt fw-5'>Cart
                  <span className='cart-count-value'>{carts?.data?.totalProduct}</span>
                </div>
              </Link>
            </div>
          </div>
        </div>

        <div className='navbar1-bottom bg-regal-blue'>
          <div className='container flex flex-between'>
            <ul className={`nav-links flex ${isSidebarOpen ? 'show-nav-links' : ""}`}>
              <button type="button" className='navbar1-hide-btn text-white' onClick={() => setIsSidebarOpen(false)}>
                <i className='fas fa-times'></i>
              </button>
              {
                links.map(link => (
                  <li key={link.id}><Link to={link.url} className="nav-link text-white" onClick={() => setIsSidebarOpen(false)}>{link.text}</Link></li>
                ))
              }
              <div className="navbar1-btns">
                <div className='navbar1-brand1'>
                  <span className="text-white">
                    {userInfo ? (
                      <NavDropdown title={`Xin ch??o, ${userInfo.name}`} id='nav-dropdown'>
                        {renderMenuControl()}
                        {/* <LinkContainer to='/profile'>
                          <NavDropdown.Item>Th??ng tin</NavDropdown.Item>
                        </LinkContainer> */}
                        <NavDropdown.Item onClick={handleShowInfo} style={{ color: '#03a9f3' }}>Th??ng tin ng?????i d??ng</NavDropdown.Item>
                        <LinkContainer to='/changepassword'>
                          <NavDropdown.Item>?????i m???t kh???u</NavDropdown.Item>
                        </LinkContainer>
                        <LinkContainer to='/myorder'>
                          <NavDropdown.Item>????n h??ng c???a t??i</NavDropdown.Item>
                        </LinkContainer>
                        <NavDropdown.Item onClick={logoutHandler}>????ng xu???t</NavDropdown.Item>
                      </NavDropdown>
                    ) : <LinkContainer to='/login'>
                      <Nav.Link className='text-primary'>
                        <i className='fas fa-user'></i> ????ng nh???p
                      </Nav.Link>
                    </LinkContainer>}
                  </span>
                </div>
              </div>

            </ul>

            <button type="button" className='navbar1-show-btn text-gold' onClick={() => setIsSidebarOpen(true)}>
              <i className="fas fa-bars"></i>
            </button>
          </div>
        </div>
      </div>

      {/* Update Profile User */}
      <Modal show={showInfo} onHide={handleCloseInfo} className='mt-5' style={{ position: 'fixed', zIndex: '11000' }}>
        <Modal.Header closeButton>
          <Modal.Title>Th??ng tin ng?????i d??ng</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p className='text-center' style={{ color: 'red' }}>{message}</p>
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label style={{ fontSize: '14px' }}>T??n ng?????i d??ng</Form.Label>
              <Form.Control
                style={{ fontSize: '14px' }}
                value={name}
                onChange={(e) => setName(e.target.value)}
                type="text"
                placeholder="Nh???p t??n ng?????i d??ng"
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label style={{ fontSize: '14px' }}>S??? ??i???n tho???i</Form.Label>
              <Form.Control
                style={{ fontSize: '14px' }}
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
                type="text"
                placeholder="Nh???p s??? ??i???n tho???i"
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label style={{ fontSize: '14px' }}>?????a ch???</Form.Label>
              <Form.Control
                style={{ fontSize: '14px' }}
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                type="text"
                placeholder="Nh???p ?????a ch???"
                autoFocus
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button style={{ fontSize: '14px', textTransform: 'none', width: 'auto' }} variant="primary" onClick={updateHandler}>
            C???p nh???t
          </Button>
        </Modal.Footer>
      </Modal>
    </nav>
  )
}

export default Navbar;