/*
  강의를 따라 @mui/material 하나만 설치했더니 오류가 발생했다.
  해결 방법을 찾던 중, 아래와 같은 글을 발견했다.

  📌 MUI v5부터는 컴포넌트와 스타일 시스템이 분리되었기 때문에
　　　@mui/material은 UI 컴포넌트만 제공하고, 실제 스타일 처리는 Emotion이 담당합니다.

  즉, v5부터는 @mui/material뿐만 아니라, 아래 2개도 함께 설치해 주어야 한다.
  - npm install @emotion/react
  - npm install @emotion/styled

  (참고)
  일부 사용법도 약간 달라진 것 같다.
  자세한 내용은 아래 공식 문서를 참고하길 바란다.
  https://mui.com/material-ui/getting-started
*/

import { Button, ButtonGroup, Container, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Grid } from "@mui/material";
import { useState } from "react";

function Header(props) {
  return (
    <header>
      <h1>Welcome!</h1>
    </header>
  );
}

function Nav() {
  return (
    <nav>
      <ol>
        <li>HTML</li>
        <li>CSS</li>
      </ol>
    </nav>
  );
}

function Article() {
  const [open, setOpen] = useState(false);

  return (
    <article>
      <h2>Welcome!</h2>
      Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
      Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
      Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
      Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
      <br />
      <br />
      <ButtonGroup>
        <Button variant="outlined" onClick={() => setOpen(true)}>Create</Button>
        <Button variant="outlined">Update</Button>
      </ButtonGroup>
      <Button variant="outlined">Delete</Button>

      <Dialog open={open}>
        <DialogTitle>제목입니다.</DialogTitle>
        <DialogContent>
          <DialogContentText>내용입니다.</DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button variant="outlined">작성</Button>
          <Button variant="outlined" onClick={() => setOpen(false)}>취소</Button>
        </DialogActions>
      </Dialog>
    </article>
  );
}

function App() {
  return (
    <Container fixed>
      <Header></Header>

      {/*
        Grid는 가로 한 줄을 12칸으로 나눠서 레이아웃을 잡는다.
        즉, 각 Grid item은 12칸 중 몇 칸을 차지할지를 지정해야 한다.
      */}
      <Grid container>
        <Grid size={2}>
          <Nav></Nav>
        </Grid>
        <Grid size={10}>
          <Article></Article>
        </Grid>
      </Grid>

    </Container>
  );
}

export default App;