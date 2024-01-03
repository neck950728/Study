import styled from "styled-components";

/*
    (CSS 자동 완성 기능)
    Marketplace : 'vscode-styled-components' 설치
*/

const StyledParentButton = styled.button`
    color:white;
    background-color:green;
`;

const StyledChildButton = styled(StyledParentButton)`
    font-size:50px;
`;

// ======================================================================

// https://www.youtube.com/watch?v=j-JxASock0Q : 7분 34초 ~ 10분 22초 참고
const NormalParentButton = props=>{
    return <button className={props.className}>{props.children}</button>
}

const NormalChildButton = styled(NormalParentButton)`
    font-size:50px;
`;

// ======================================================================

const PrimaryButton = styled.button`
    /*
        color:${function(props){
            console.log(props);
            if(props.primary){
                return "red";
            }else{
                return "black";
            }
        }};
    */
    color:${props=>props.primary ? "red" : "black"};
`;

function App() {
    return (
        <div>
            <StyledParentButton>Parent</StyledParentButton>
            <StyledChildButton>Child</StyledChildButton>
            <hr />
            <NormalParentButton>Parent</NormalParentButton>
            <NormalChildButton>Child</NormalChildButton>
            <hr />
            <PrimaryButton primary>Primary</PrimaryButton>
            <PrimaryButton>NotPrimary</PrimaryButton>
        </div>
    );
}
  
export default App;