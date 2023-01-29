export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        name: 'login',
        path: '/user/login',
        component: './user/Login',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  {
    path: '/usermanager',
    name: '用户管理',
    icon: 'TeamOutlined',
    component: './UserManger/UserManger'
  },
  {
    path: '/goods',
    name: '商品管理',
    icon: 'FolderOpenOutlined',
    component: './Goods/Goods'
  },
  {
    path: '/instorage',
    name: '入库管理',
    icon: 'FundViewOutlined',
    component: './Instorage/InStorage'
  },
  {
    path: '/outstorage',
    name: '出库管理',
    icon: 'FundViewOutlined',
    component: './OutStorage/OutStorage'
  },
  {
    path: '/statistic',
    name: '进销存统计',
    icon: 'table',
    component: './statistic/statistic'
  },
  {
    path: '/admin',
    name: 'admin',
    icon: 'crown',
    access: 'canAdmin',
    routes: [
      {
        path: '/admin/sub-page',
        name: 'sub-page',
        icon: 'smile',
        component: './Welcome',
      },
      {
        component: './404',
      },
    ],
  },
  {
    name: 'list.table-list',
    icon: 'table',
    path: '/list',
    component: './TableList',
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  },
];
