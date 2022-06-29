export default function StudentTable(props){
    return(
        <tr>
          <td>{props.std[0]}</td>
          <td>{props.std[1]}</td>
          <td>{props.std[2]}</td>
          {window.localStorage.getItem("role") === "true" && <td>{props.std[3]}</td>}
        </tr>
      )
}