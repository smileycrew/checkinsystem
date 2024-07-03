export default function Button({ children, isCheckingIn, onClick }) {
  return (
    <button
      className="active:scale-[105%] border hover:scale-[110%] hover:shadow-md px-3 py-2 rounded-full shadow-sm transition"
      disabled={isCheckingIn}
      onClick={onClick}
    >
      {children}
    </button>
  )
}
