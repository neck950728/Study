import { Avatar, Box, Button, Checkbox, Container, FormControlLabel, Grid, Link, TextField, Typography } from "@mui/material";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined"

function App() {
  return (
    <Container component="main" maxWidth="xs">
      <Box sx={{
        display:'flex',
        flexDirection:'column',
        alignItems:'center',
        marginTop:8
      }}>
        <Avatar sx={{m:1, bgcolor:'secondary.main'}}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
        <TextField
          name="email"
          label="Email Address"
          required
          fullWidth
          autoComplete="email"
          autoFocus
          margin="normal"
        />
        <TextField
          name="password"
          type="password"
          label="Password"
          required
          fullWidth
          autoComplete="current-password"
          margin="normal"
        />
        <FormControlLabel
          control={<Checkbox value="remember" color="primary" />}
          label="Remember me"
        />
        <Button
          type="submit"
          fullWidth
          variant="contained"
          sx={{mt:3, mb:2}}
        >
        Sign in
        </Button>
        <Grid container justifyContent="space-between" sx={{width:'100%'}}>
          <Grid>
            <Link>Forgot your password?</Link>
          </Grid>
          <Grid>
            <Link>Sign up</Link>
          </Grid>
        </Grid>
      </Box>
    </Container>
  );
}

export default App;