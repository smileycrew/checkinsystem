export default function Input({ checked, onChange, placeholder, type, value }) {
  return (
    <input
      className={`border px-3 py-2 rounded ${
        type !== "checkbox" && "shadow-sm"
      } w-full`}
      checked={checked}
      onChange={onChange}
      placeholder={placeholder}
      required
      type={type || "text"}
      value={value}
    />
  )
}
