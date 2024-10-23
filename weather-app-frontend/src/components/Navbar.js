// Navbar.js
import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  // Define your styles
  const navbarStyle = {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    backgroundColor: 'rgba(0, 0, 0, 0.5)', // Updated background color
    padding: '10px',
    boxShadow: '0 2px 5px rgba(0, 0, 0, 0.2)',
    fontFamily: 'Arial, sans-serif', // Set font family
  };

  const titleStyle = {
    color: 'white',
    margin: 0,
    fontSize: '24px',
  };

  const listStyle = {
    listStyleType: 'none',
    display: 'flex',
    margin: 0,
    padding: 0,
  };

  const itemStyle = {
    marginLeft: '20px',
  };

  const linkStyle = {
    textDecoration: 'none',
    color: 'white',
    fontWeight: 'bold',
    padding: '8px 15px',
    transition: 'background-color 0.3s ease',
    backgroundColor: 'transparent',
    borderRadius: '5px',
  };

  const linkHoverStyle = {
    backgroundColor: '#0056b3', // Darker color on hover
  };

  return (
    <nav style={navbarStyle}>
      <h1 style={titleStyle}>Weather Monitoring 
        System</h1> {/* Title of your application */}
      <ul style={listStyle}>
        {/* Define your navigation links */}
        <li style={itemStyle}>
          <Link
            to="/"
            style={linkStyle}
            onMouseEnter={(e) => e.currentTarget.style.backgroundColor = linkHoverStyle.backgroundColor}
            onMouseLeave={(e) => e.currentTarget.style.backgroundColor = 'transparent'}
          >
            Home
          </Link>
        </li>
        <li style={itemStyle}>
          <Link
            to="/summaries"
            style={linkStyle}
            onMouseEnter={(e) => e.currentTarget.style.backgroundColor = linkHoverStyle.backgroundColor}
            onMouseLeave={(e) => e.currentTarget.style.backgroundColor = 'transparent'}
          >
            Weather Summaries
          </Link>
        </li>
        <li style={itemStyle}>
          <Link
            to="/alerts"
            style={linkStyle}
            onMouseEnter={(e) => e.currentTarget.style.backgroundColor = linkHoverStyle.backgroundColor}
            onMouseLeave={(e) => e.currentTarget.style.backgroundColor = 'transparent'}
          >
            Alerts
          </Link>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
