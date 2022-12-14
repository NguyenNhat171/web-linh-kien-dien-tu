import React, { useEffect, useState } from 'react'
import { LinkContainer } from 'react-router-bootstrap'
import { Table, Button, Row, Col, Pagination, Modal, Form, Image } from 'react-bootstrap'
import { ResponsiveContainer, LineChart, Line, BarChart, Bar, CartesianGrid, XAxis, YAxis, Tooltip, Legend } from 'recharts';
import { useDispatch, useSelector } from 'react-redux'
import ReactTooltip from 'react-tooltip'
import Message from '../../components/Message'
import Loader from '../../components/Loader'
import { getAllOrders, getProfitOrder, listOrderAdmin } from "../../actions/orderActions";
import { Link, useNavigate } from 'react-router-dom'
import { getAllUsersAdmin } from '../../actions/userActions'
import { getAllCommentsAdmin, getAllProductsAdmin, listCategoryAdmin } from '../../actions/productActions'

const DashboardScreen = () => {
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const [type, setType] = useState('month')
  console.log('===', type)

  const { userAll } = useSelector((state) => state.userAllAdmin)
  const { categories } = useSelector((state) => state.categoryListAdmin)
  const { productAll } = useSelector(state => state.productAllAdmin)
  const { orderAll } = useSelector(state => state.orderAll)
  const { profit } = useSelector(state => state.orderProfit)
  const { reviews } = useSelector((state) => state.reviewAllAdmin)
  // console.log('====', orderAll?.data)

  const arrOrderAll = []
  const checkOrderAll = () => {
    orderAll?.data?.list?.find(item => {
      if (item.state !== 'in cart') {
        arrOrderAll.push(item)
      }
    })
  }
  checkOrderAll()

  const userLogin = useSelector((state) => state.userLogin)
  const { userInfo } = userLogin

  useEffect(() => {
    if (userInfo || userInfo.role === "role_admin") {
      dispatch(getAllOrders())
      dispatch(getAllUsersAdmin())
      dispatch(listCategoryAdmin())
      dispatch(getAllProductsAdmin())
      dispatch(getAllCommentsAdmin())
      dispatch(getProfitOrder('01-11-2020', '28-11-2025', type))
    } else {
      navigate('/login')
    }
  }, [dispatch, navigate, userInfo])


  return (
    <div style={{ overflowY: 'scroll', height: '100%', width: '100%', fontSize: '14px', background: '#edf1f5' }}>
      <div className='d-flex align-items-center justify-content-between flex-wrap px-4' style={{ background: 'white', width: '100%' }}>
        <div className='d-flex align-items-center justify-content-between py-4'>
          <div className='d-flex align-items-center'>
            <i className='fas fa-home'></i>
            <a href='/admin/dashboard' className='my-0 mx-1' style={{ textDecoration: 'none', color: 'black' }}>Trang ??i???u khi???n</a>
          </div>
        </div>
      </div>
      <Row className='d-flex justify-content-between align-items-center mx-4 flex-wrap'>
        <Col xl={2} className='d-flex justify-content-center align-items-center flex-column px-0 my-4 py-3' style={{ background: 'white' }}>
          <h6 style={{ fontSize: '14px' }} className='pb-4 pt-4 my-0'>Ng?????i d??ng: {userAll?.data?.length}</h6>
          <Link style={{ textDecoration: 'none' }} to='/admin/userlist'>Chi ti???t</Link>
        </Col>
        <Col xl={2} className='d-flex justify-content-center align-items-center flex-column px-0 my-4 py-3' style={{ background: 'white' }}>
          <h6 style={{ fontSize: '14px' }} className='pb-4 pt-4 my-0'>Danh m???c: {categories?.data?.length}</h6>
          <Link style={{ textDecoration: 'none' }} to='/admin/categorylist'>Chi ti???t</Link>
        </Col>
        <Col xl={2} className='d-flex justify-content-center align-items-center flex-column px-0 my-4 py-3' style={{ background: 'white' }}>
          <h6 style={{ fontSize: '14px' }} className='pb-4 pt-4 my-0'>S???n ph???m: {productAll?.data?.length}</h6>
          <Link style={{ textDecoration: 'none' }} to='/admin/productlist'>Chi ti???t</Link>
        </Col>
        <Col xl={2} className='d-flex justify-content-center align-items-center flex-column px-0 my-4 py-3' style={{ background: 'white' }}>
          <h6 style={{ fontSize: '14px' }} className='pb-4 pt-4 my-0'>????n h??ng: {arrOrderAll?.length}</h6>
          <Link style={{ textDecoration: 'none' }} to='/admin/orderlist'>Chi ti???t</Link>
        </Col>
        <Col xl={2} className='d-flex justify-content-center align-items-center flex-column px-0 my-4 py-3' style={{ background: 'white' }}>
          <h6 style={{ fontSize: '14px' }} className='pb-4 pt-4 my-0'>????nh gi??: {reviews?.data?.length}</h6>
          <Link style={{ textDecoration: 'none' }} to='/admin/commentlist'>Chi ti???t</Link>
        </Col>
      </Row>
      <Row className="section px-0 mx-3 py-0">
        <h5 className="section-title">Th???ng k?? s??? l?????ng s???n ph???m</h5>
        <div className="section-content">
          <ResponsiveContainer width="100%" height={500}>
            <BarChart data={productAll?.data} margin={{ top: 15, right: 0, bottom: 15, left: 0 }}>
              <XAxis dataKey="name" />
              <YAxis />
              <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
              <Tooltip />
              <Legend />
              {/* <Bar dataKey="totalAvenue" fill="#FB8833" /> */}
              <Bar dataKey="quantity" fill="#17A8F5" name='S??? l?????ng s???n ph???m' />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </Row>
      <Row className='d-flex justify-content-between align-items-center mx-4 flex-wrap'>
        <Col xl={5} className='d-flex justify-content-center align-items-center flex-column px-0 my-4 py-3' style={{ background: 'white' }}>
          <p style={{ fontSize: '14px' }} className='pb-4 pt-4 my-0'><h4>S???n ph???m g???n h???t h??ng:</h4>
            {
              productAll?.data?.map((item, index) => {
                if (item.quantity <= 20) {
                  return (
                    <div className='d-flex justify-content-between align-items-center'>
                      <p className='py-0 my-0' key={index}>-{item.name} (c??n {item.quantity} c??i)</p>
                      <Link to={`/admin/product/${item.id}/detail`}>chi ti???t</Link>
                    </div>
                  )
                }
              })
            }
          </p>
          <Link style={{ textDecoration: 'none' }} to='/admin/userlist'>?????n trang qu???n l?? s???n ph???m</Link>
        </Col>
        <Col xl={5} className='d-flex justify-content-center align-items-center flex-column px-0 my-4 py-3' style={{ background: 'white' }}>
          <p style={{ fontSize: '14px' }} className='pb-4 pt-4 my-0'><h4>S???n ph???m c??n s??? l?????ng l???n:</h4>
            {
              productAll?.data?.map((item, index) => {
                if (item.quantity >= 100) {
                  return (
                    <div className='d-flex justify-content-between align-items-center'>
                      <p className='py-0 my-0' key={index}>-{item.name} (c??n {item.quantity} c??i)</p>
                      <Link to={`/admin/product/${item.id}/detail`}>chi ti???t</Link>
                    </div>
                  )
                }
              })
            }
          </p>
          <Link style={{ textDecoration: 'none' }} to='/admin/userlist'>?????n trang qu???n l?? s???n ph???m</Link>
        </Col>
      </Row>

      {/* Profit */}
      <Row className="section px-0 mx-3 py-0">
        <h5 className="section-title">Th???ng k?? doanh thu theo th??ng</h5>
        <div className="section-content">
          <ResponsiveContainer width="100%" height={500}>
            <BarChart data={profit?.data} margin={{ top: 15, right: 0, bottom: 15, left: 0 }}>
              <XAxis dataKey="date" />
              <YAxis />
              <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
              <Tooltip />
              <Legend />
              {/* <Bar dataKey="totalAvenue" fill="#FB8833" /> */}
              <Bar dataKey="amount" fill="green" name='T???ng doanh thu th??ng' />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </Row>
      {/* <Row className='d-flex justify-content-between align-items-center px-0 my-4 py-3 mx-0'>
        <Button style={{width: 'auto', background: '#17A8F5', border: 'none'}} onClick={() => setType('month')}>Th??ng</Button>
        <Button style={{width: 'auto', background: '#17A8F5', border: 'none'}} onClick={() => setType('all')}>N??m</Button>
      </Row> */}
    </div>
  )
}

export default DashboardScreen
