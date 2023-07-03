interface IProps {
    robert: boolean
}

const Seguimiento = (props: IProps) =>
    (
        <>
        {props.robert && 'ROBERT'}
        <div>seguimiento</div>
        </>
    )

  export default Seguimiento